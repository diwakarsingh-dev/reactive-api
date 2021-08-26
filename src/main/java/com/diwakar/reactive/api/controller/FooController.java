package com.diwakar.reactive.api.controller;

import java.time.Duration;
import java.util.stream.Stream;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diwakar.reactive.api.model.Foo;

import reactor.core.publisher.Flux;

@RestController
public class FooController {
	
	@GetMapping(value = "/reactive-result", produces = "text/event-stream")
	public Flux<Foo> streamWeather() {
        Flux<Long> interval = Flux.interval(Duration.ofSeconds(1));
        Flux<Foo> result = Flux.fromStream(Stream.generate(
                                    ()->new Foo(1, "Diwakar")));
       return Flux.zip(result, interval, (key, value) -> key);
	}
}
