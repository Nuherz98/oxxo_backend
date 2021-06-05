package mx.edu.itlapiedad.services;

import java.util.List;

import mx.edu.itlapiedad.models.tickets;

public interface ticketsService {
List<tickets> consultarTickets();
tickets buscar(int id);
tickets insertar (tickets tickets);
void actualizar(tickets tickets);

void eliminar(int id);
}
