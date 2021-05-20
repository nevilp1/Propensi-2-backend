package propensi.Pin.Insight.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import propensi.Pin.Insight.model.ERole;
import propensi.Pin.Insight.model.RoleModel;
import propensi.Pin.Insight.model.UserModel;
import propensi.Pin.Insight.payload.request.LoginRequest;
import propensi.Pin.Insight.payload.request.SignupRequest;
import propensi.Pin.Insight.payload.response.JwtResponse;
import propensi.Pin.Insight.payload.response.MessageResponse;
import propensi.Pin.Insight.repository.RoleDb;
import propensi.Pin.Insight.repository.UserDb;
import propensi.Pin.Insight.security.jwt.JwtUtils;
import propensi.Pin.Insight.security.service.UserDetailsImpl;

import javax.management.relation.Role;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserDb userDb;

    @Autowired
    RoleDb roleDb;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userDb.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userDb.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        UserModel user = new UserModel(signUpRequest.getUsername(),
                encoder.encode(signUpRequest.getPassword()),
                signUpRequest.getNama(),
                signUpRequest.getEmail(),
                signUpRequest.getTeam()
                );

        Set<String> strRoles = signUpRequest.getRole();
        Set<RoleModel> roles = new HashSet<>();

        if (strRoles == null) {
            RoleModel userRole = roleDb.findByNamaRole(ERole.ROLE_RESEARCHER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "Admin":
                        RoleModel adminRole = roleDb.findByNamaRole(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "Head of Product Design & Research":
                        RoleModel headRole = roleDb.findByNamaRole(ERole.ROLE_HEAD_OF_RESEARCHER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(headRole);

                        break;
                    default:
                        RoleModel userRole = roleDb.findByNamaRole(ERole.ROLE_RESEARCHER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);

        if (signUpRequest.getPassword().equals(signUpRequest.getRepassword())){
            user.setStatus(true);
            userDb.save(user);
        } else {
            return null;
        }


        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
    @PutMapping("/user/{id}/update")
    public ResponseEntity<?> updateUser(@Valid @PathVariable(value = "id") Long id, @RequestBody SignupRequest editUser) {
        System.out.println("Masuk Sini");

        UserModel user = userDb.findById(id).get();
        user.setTeam(editUser.getTeam());

        Set<String> strRoles = editUser.getRole();
        Set<RoleModel> roles = new HashSet<>();

        if (strRoles == null) {
            RoleModel userRole = roleDb.findByNamaRole(ERole.ROLE_RESEARCHER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "Admin":
                        RoleModel adminRole = roleDb.findByNamaRole(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "Head of Product Design & Research":
                        RoleModel headRole = roleDb.findByNamaRole(ERole.ROLE_HEAD_OF_RESEARCHER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(headRole);

                        break;
                    default:
                        RoleModel userRole = roleDb.findByNamaRole(ERole.ROLE_RESEARCHER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);

        if (editUser.getPassword().length() <6 ) {
//            System.out.println("masuk");
//            System.out.println(editUser.getPassword());
//            System.out.println(editUser.getRepassword());
//            String error = "Malformed JSON request";
//            return error;
            return null;
        } else if (editUser.getPassword().length() >= 6  && editUser.getPassword().equals(editUser.getRepassword())) {
//            System.out.println("keganti");
            user.setPassword(encoder.encode(editUser.getPassword()));
            user.setStatus(true);
            userDb.save(user);
        }


        return ResponseEntity.ok(new MessageResponse("User updated!"));
    }
}
