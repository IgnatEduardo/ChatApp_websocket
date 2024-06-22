package com.eduardo.websocket.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.DefaultContentTypeResolver;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.*;

/**
 * Configuration class that sets up WebSocket messaging in the Spring Boot application.
 * It enables WebSocket message handling and configures the message broker,
 * STOMP endpoints, and message converters.
 */
@Configuration // Indicates that this is a configuration class for Spring
@EnableWebSocketMessageBroker // Enables WebSocket message handling with a message broker
public class WebSockerConfig implements WebSocketMessageBrokerConfigurer {

    /**
     * Configures the message broker, which is responsible for routing messages from one client to another.
     * This method sets up the message broker with specific destination prefixes for application-level
     * and user-level messaging.
     *
     * @param registry the MessageBrokerRegistry used to configure the message broker.
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // Enables a simple in-memory message broker with the destination prefix "/user"
        registry.enableSimpleBroker("/user");
        // Sets the application destination prefix to "/app", which is used for messages bound for @MessageMapping methods
        registry.setApplicationDestinationPrefixes("/app");
        // Sets the user destination prefix to "/user", used for messages directed to specific users
        registry.setUserDestinationPrefix("/user");
    }

    /**
     * Registers STOMP endpoints that clients will use to connect to the WebSocket server.
     * This method defines the endpoint URL for WebSocket connections and enables SockJS as a fallback option.
     *
     * @param registry the StompEndpointRegistry used to register the STOMP endpoints.
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // Registers the "/ws" endpoint for WebSocket connections and enables SockJS for clients that don't support WebSocket
        registry.addEndpoint("/ws").withSockJS();
    }

    /**
     * Configures the message converters to use JSON format for WebSocket messages.
     * This method sets up a JSON message converter and adds it to the list of message converters.
     *
     * @param messageConverters the list of message converters to which the JSON converter is added.
     * @return false to indicate that default converters should not be added automatically.
     */
    @Override
    public boolean configureMessageConverters(List<MessageConverter> messageConverters) {
        // Creates a content type resolver that defaults to MIME type JSON
        DefaultContentTypeResolver resolver = new DefaultContentTypeResolver();
        resolver.setDefaultMimeType(APPLICATION_JSON);

        // Creates a message converter that converts WebSocket messages to and from JSON format
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setObjectMapper(new ObjectMapper());
        converter.setContentTypeResolver(resolver);

        // Adds the JSON message converter to the list of converters
        messageConverters.add(converter);
        return false; // Indicates that default converters should not be added
    }
}
