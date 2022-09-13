package br.ufrn.imd.dominio;

import java.util.ArrayList;
import java.util.Date;

public class Aluno extends Pessoa {
    private String matricula;
    private double IRA;
    private Date dataMatricula;
    private String curso;
    private ArrayList<Disciplina> disciplinas;

    public void Pessoa() {
        this.disciplinas = new ArrayList<Disciplina>();
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public double getIRA() {
        return IRA;
    }

    public void setIRA(double iRA) {
        IRA = iRA;
    }

    public Date getDataMatricula() {
        return dataMatricula;
    }

    public void setDataMatricula(Date dataMatricula) {
        this.dataMatricula = dataMatricula;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public ArrayList<Disciplina> getDisciplinas() {
        return this.disciplinas;
    }

    public void setDisciplinas(ArrayList<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

    public boolean getIsEmptyDisciplinas(){
        return this.disciplinas.isEmpty();
    }

    public void realizarMatricula(ArrayList<Disciplina> disciplinas) {
        if (disciplinas.size() > 0) {
            this.disciplinas = disciplinas;
        }
    }

    public void realizarMatricula(Disciplina disciplina) {
        this.disciplinas.add(disciplina);
    }

    public boolean trancarDisciplinaById(int idDisciplinaParaTrancar){
        boolean trancouComSucesso = false;
        for (Disciplina d : disciplinas) {
            if(d.getId() == idDisciplinaParaTrancar){
                d.trancar();
                trancouComSucesso = true;
            }
        }
        return trancouComSucesso;
    }

    @Override
    public String toString() {
        String stringToReturn = "";

        stringToReturn += "{\n";
        stringToReturn += "  Aluno: " + this.nome + "\n";
        stringToReturn += "  Matricula: " + this.matricula + "\n";
        stringToReturn += "  Disciplinas: [ " + "\n";
        for (int i = 0; i < disciplinas.size(); i++) {
            stringToReturn += "                  " + i +": " + disciplinas.get(i) + "\n";
        }
        stringToReturn += "               ]" + "\n";
        stringToReturn += "}";
        return stringToReturn;
    }
}
