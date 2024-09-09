package com.example.demo.infra.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "Api de Contactos  y direcciones Online.",
                description = "API de contactos y direcciones online" +
                        " podras crear eliminar actualizar contactos todo" +
                        " con autenticacion de usuarios y seguridad por roles.",
                termsOfService = "terminos y servicio libre",
                version = "v1.0.1",
                contact =@Contact(
                        name = "EDWIN CASTRO",
                         url = "https://edtecnology.com/",
                        email = "castromaradiaga@hotmail.com"
                ),
                license = @License(
                        name = "libre de uso",
                        url = "https://edtecnology.com/"
                )
        ),
        servers = {
                @Server(
                        url = "http://localhost:8080",
                        description = "SERVER DE DESARROLLO"
                )

        },
        security = @SecurityRequirement(
                name = "contacto security"
        )
)
@SecurityScheme(
        name = "contacto security",
        description = "seguridad con token y roles de usuario",
        type = SecuritySchemeType.HTTP,
        paramName = "Authorization",
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER,
        scheme = "bearer"
)
public class ConfigurationSawgger {
}
