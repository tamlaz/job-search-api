package com.lazartamas.jobsearchapi.configuration;

import com.lazartamas.jobsearchapi.domain.Client;
import com.lazartamas.jobsearchapi.repository.ClientRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.List;

@Component
public class ApiKeyInterceptor implements HandlerInterceptor {

    private final ClientRepository clientRepository;
    private List<String> excludedEndPoints = List.of("/api/clients/register", "/v3/api-docs", "/v3/api-docs/swagger-config");

    public ApiKeyInterceptor(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String apiKey = request.getHeader("apiKey");
        Client client = clientRepository.findClientByApiKey(apiKey).orElse(null);

        if (excludedEndPoints.contains(request.getRequestURI()) || request.getRequestURI().startsWith("/swagger")) {
            return true;
        }

        if (apiKey == null || client == null) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write("Invalid API Key");
            return false;
        }
        return true;
    }


}
