Contract.make {
    description "Debe devolver un usuario cuando se consulta por ID"

    request {
        method GET()
        url "/users/1"
    }

    response {
        status 200
        body([
            id: 1,
            name: "John Doe",
            email: "john.doe@example.com"
        ])
        headers {
            contentType applicationJson()
        }
    }
}
