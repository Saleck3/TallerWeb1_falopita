package ar.edu.unlam.tallerweb1.infrastructure.suenio;

import ar.edu.unlam.tallerweb1.domain.suenio.Suenio;

import java.util.List;

public interface RepositorioSuenio {

   public List<Suenio> TraerPorEdadDeterminada(int edad);
   public List<Suenio> listar();
}
