package br.ufrn.imd.dominio;

import java.util.ArrayList;

public class Disciplina {
    private int id;
    private String nomeDisciplina;
    private Curso curso;
    private boolean trancado;
    private ArrayList<Integer> requisitos;
    private ArrayList<Integer> coRequisitos;

    public Disciplina() {
        this.requisitos = new ArrayList<Integer>();
        this.coRequisitos = new ArrayList<Integer>();
    }

    public Disciplina(String nomeDisciplina) {
        this.nomeDisciplina = nomeDisciplina;
        this.requisitos = new ArrayList<Integer>();
        this.coRequisitos = new ArrayList<Integer>();
    }

    public Disciplina(int id, String nomeDisciplina, ArrayList<Integer> requisitos,
            ArrayList<Integer> coRequisitos) {
        this.nomeDisciplina = nomeDisciplina;
        this.requisitos = new ArrayList<Integer>();
        this.coRequisitos = new ArrayList<Integer>();
        this.requisitos.addAll(requisitos);
        this.coRequisitos.addAll(coRequisitos);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeDisciplina() {
        return nomeDisciplina;
    }

    public void setNomeDisciplina(String nomeDisciplina) {
        this.nomeDisciplina = nomeDisciplina;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public ArrayList<Integer> getRequisitos() {
        return requisitos;
    }

    public void setRequisitos(ArrayList<Integer> requisitos) {
        this.requisitos = requisitos;
    }

    public ArrayList<Integer> getCoRequisitos() {
        return coRequisitos;
    }

    public void setCoRequisitos(ArrayList<Integer> coRequisitos) {
        this.coRequisitos = coRequisitos;
    }

    public boolean getTrancado() {
        return this.trancado;
    }

    public void setTrancado(boolean trancado) {
        this.trancado = trancado;
    }

    public void trancar() {
        this.trancado = true;
    }

    public void addRequisito(int idDisciplina) {
        requisitos.add(idDisciplina);
    }

    public void addCoRequisito(int idDisciplina) {
        coRequisitos.add(idDisciplina);
    }

    public void removeRequisitoById(int idDisciplinaToRemove) {
        for (int i = 0; i < requisitos.size(); i++) {
            if (requisitos.get(i) == idDisciplinaToRemove) {
                requisitos.remove(i);
            }
        }
    }

    public void removeCoRequisitoById(int idDisciplinaToRemove) {
        for (int i = 0; i < coRequisitos.size(); i++) {
            if (coRequisitos.get(i) == idDisciplinaToRemove) {
                coRequisitos.remove(i);
            }
        }
    }

    public boolean temRequisitos(){
        return this.requisitos.size() > 0;
    }

    public boolean temCoRequisitos(){
        return this.coRequisitos.size() > 0;
    }

    @Override
    public String toString() {
        if (this.trancado) {
            return this.nomeDisciplina.toUpperCase() + " - TRANCADO";
        } else {
            return this.nomeDisciplina.toUpperCase() + " - CURSANDO";
        }
    }

}
