package org.classes.api.router;

import org.classes.api.domain.dto.GymClassDTO;
import org.classes.api.usecases.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class GymClassRouter {

    @Bean
    public RouterFunction<ServerResponse> getAllGymClasses(GetAllGymClassesUseCase useCase) {
        return route(GET("/classes"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(useCase.get(), GymClassDTO.class))
                        .onErrorResume(throwable -> ServerResponse.noContent().build()));
    }

    @Bean
    public RouterFunction<ServerResponse> getGymCLassById(GetGymCLassByIdUseCase useCase){
        return route(GET("/classes/{id}"),
                request -> useCase.apply(request.pathVariable("id"))
                        .flatMap(gymClassDTO -> ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(gymClassDTO))
                        .onErrorResume(throwable -> ServerResponse.notFound().build()));
    }

    @Bean
    public RouterFunction<ServerResponse> saveGymClass(SaveGymClassUseCase useCase) {
        return route(POST("/classes").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(GymClassDTO.class)
                        .flatMap(gymClassDTO -> useCase.save(gymClassDTO)
                                .flatMap(result -> ServerResponse.status(201)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result))
                                .onErrorResume(throwable -> ServerResponse.status(HttpStatus.NOT_ACCEPTABLE).build())));
    }

    @Bean
    public  RouterFunction<ServerResponse> updateGymClass(UpdateGymCLassUseCase useCase){
        return route(PUT("/classes/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(GymClassDTO.class)
                        .flatMap(gymClassDTO -> useCase.update(request.pathVariable("id"), gymClassDTO)
                                .flatMap(result -> ServerResponse.status(201)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result))
                                .onErrorResume(throwable -> ServerResponse.status(HttpStatus.NOT_ACCEPTABLE).build())));
    }

    @Bean
    public RouterFunction<ServerResponse> deleteGymCLass(DeleteGymClassUseCase useCase) {
        return route(DELETE("/classes/{id}"),
                request -> useCase.delete(request.pathVariable("id"))
                        .flatMap(s -> ServerResponse.ok()
                                .bodyValue("Class with id "+s+" has been deleted"))
                        .onErrorResume(throwable -> ServerResponse.notFound().build()));

    }


}
