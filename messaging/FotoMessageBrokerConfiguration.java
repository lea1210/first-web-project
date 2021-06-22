
@Configuration
@EnableWebSocketMessageBroker
public class FotoMessageBrokerConfiguration {

    @Override public void configureMessageBroker(MessageBrokerRegistry registry) {
        // Prefix für alle zugehörigen Destinations,// z.B. /topic/news, /topic/offers usw.
        registry.enableSimpleBroker("/topic");
    
    
    }@Override public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/messagebroker").setAllowedOrigins("*");
    }
    
}
