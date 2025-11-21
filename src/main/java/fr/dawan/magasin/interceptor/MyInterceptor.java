package fr.dawan.magasin.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class MyInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        // Enregistrer le temps de début
        request.setAttribute("startTime", System.currentTimeMillis());

        // Logger la requête entrante
        System.out.println("══════════════════════════════════════════");
        System.out.println("[REQUEST] " + request.getMethod() + " " + request.getRequestURI());
        System.out.println("[IP] " + request.getRemoteAddr());
        System.out.println("[User-Agent] " + request.getHeader("User-Agent"));

        return true; // true = continuer, false = bloquer la requête
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {
        // Ajouter des attributs globaux au Model (si disponible)
        if (modelAndView != null) {
            modelAndView.addObject("appName", "Magasin");
            modelAndView.addObject("appVersion", "1.0.0");
        }
        System.out.println("[POST-HANDLE] Vue: " + (modelAndView != null ? modelAndView.getViewName() : "N/A"));
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {
        // Calculer le temps d'exécution
        long startTime = (Long) request.getAttribute("startTime");
        long duration = System.currentTimeMillis() - startTime;

        // Logger la réponse
        System.out.println("[RESPONSE] Status: " + response.getStatus() + " - Durée: " + duration + "ms");

        // Logger les erreurs si présentes
        if (ex != null) {
            System.out.println("[ERROR] " + ex.getMessage());
        }
        System.out.println("══════════════════════════════════════════");
    }
}
