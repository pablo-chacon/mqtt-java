CREATE TABLE IF NOT EXISTS "user" (
                                    iduser INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                                    username VARCHAR(100) NOT NULL,
                                    password VARCHAR(100) NOT NULL,
                                    UNIQUE (iduser)
);

CREATE TABLE IF NOT EXISTS "user_sessions" (
                                               session_id INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                                               user_id INT NOT NULL,
                                               start_time TIMESTAMP NOT NULL,
                                               end_time TIMESTAMP NOT NULL,
                                               FOREIGN KEY (user_id) REFERENCES "user"(iduser)
);


CREATE TABLE IF NOT EXISTS "geodata" (
                                         idgeo INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                                         latitude DOUBLE PRECISION NULL DEFAULT NULL,
                                         longitude DOUBLE PRECISION NULL DEFAULT NULL,
                                         timestamp TIMESTAMP NULL DEFAULT NULL,
                                         user_id INT NOT NULL,
                                         session_id INT NOT NULL,
                                         FOREIGN KEY (user_id) REFERENCES "user"(iduser),
                                         FOREIGN KEY (session_id) REFERENCES "user_sessions"(session_id)
);

CREATE TABLE IF NOT EXISTS "routes" (
                                        route_id INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                                        session_id INT NOT NULL,
                                        optimized_route_data TEXT,
                                        FOREIGN KEY (session_id) REFERENCES "user_sessions"(session_id)
);
