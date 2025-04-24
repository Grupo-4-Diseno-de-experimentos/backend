package pe.edu.upc.trabajo.Trabajo.user.domain.model.queries;

public record GetUserByEmailAndPasswordQuery(String email,
                                             String password) {
}
