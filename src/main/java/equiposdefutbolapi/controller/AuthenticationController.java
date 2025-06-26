package equiposdefutbolapi.controller;

import equiposdefutbolapi.dto.request.AuthenticationRequest;
import equiposdefutbolapi.dto.response.AuthenticationResponse;
import equiposdefutbolapi.exception.BadRequestException;
import equiposdefutbolapi.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {
        validateRequest(request);
        return ResponseEntity.ok(service.authenticate(request));
    }

    private void validateRequest(AuthenticationRequest request) {
        if (request.getUsername() == null || request.getPassword() == null) {
            throw new BadRequestException();
        }
    }

}
