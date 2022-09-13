package br.ufrn.imd.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner; 

import br.ufrn.imd.dominio.Aluno;
import br.ufrn.imd.dominio.Disciplina; 

public class App {
    public static void main(String[] args) throws Exception {
        Scanner f = new Scanner(System.in);
        try {
            // DADOS BÁSICOS DO ALUNO
            Aluno aluno1 = new Aluno();
            System.out.println("Digite o nome do aluno: ");
            aluno1.setNome(f.nextLine());
            System.out.println("Digite a matrícula do aluno:");
            aluno1.setMatricula(f.nextLine());
            System.out.println("Digite qual o curso do aluno:");
            aluno1.setCurso(f.nextLine());
            Calendar dataNascimento = Calendar.getInstance();
            dataNascimento.set(Calendar.DATE, 11);
            dataNascimento.set(Calendar.MONTH, 03);
            dataNascimento.set(Calendar.YEAR, 2001);
            aluno1.setDataMatricula(dataNascimento.getTime());

            // CADASTRO DE DISCIPLINAS ANTES DE REALIZAR MATRÍCULA
            int x = 0;
            ArrayList<Disciplina> disciplinas = new ArrayList<Disciplina>();
            ArrayList<Disciplina> disciplinasCadastradas = new ArrayList<Disciplina>();

            //como deixar dinâmico? 
            String pathAbsoluto = new File("").getAbsolutePath();
            String pathCompleto = pathAbsoluto.concat("/src/br/ufrn/imd/main/disciplinas.txt");
            Scanner in = new Scanner(new FileReader(pathCompleto));

            while (in.hasNextLine()) {
                String line = in.nextLine();
                String[] itens = line.split(";");
                Disciplina d = new Disciplina();
                d.setId(Integer.parseInt(itens[0]));
                d.setNomeDisciplina(itens[1]);
                if (!itens[2].equals("not")) {
                    d.addRequisito(Integer.parseInt(itens[2]));
                }
                if (!itens[3].equals("not")) {
                    d.addCoRequisito(Integer.parseInt(itens[3]));
                }
                disciplinasCadastradas.add(d);
            }

            while (x != -1) {
                System.out.println("\n--------MENU DE CADASTRO DE DISCIPLINAS--------");
                System.out.println("Digite 1 para adicionar uma disciplina.");
                System.out.println("Digite 2 para remover uma disciplina.");
                System.out.println("Digite 3 para realizar a matrícula.");
                System.out.println("Digite -1 para encerrar.");

                x = f.nextInt();

                switch (x) {
                    case -1:
                        System.out.println("Cadastro de disciplinas encerrado");
                        break;
                    case 1:
                        System.out.println("DISCIPLINAS DISPONÍVEIS: ");

                        for (Disciplina d : disciplinasCadastradas) {
                            System.out.println(d.getId() + " - " + d.getNomeDisciplina());
                        }

                        System.out.println("Digite uma disciplina do aluno de acordo com o id:");
                        int idDisciplina = f.nextInt();
                        Disciplina disciplinaParaAdd = disciplinasCadastradas.get(idDisciplina - 1);
                        disciplinas.add(disciplinaParaAdd);
                        break;
                    case 2:
                        if (disciplinas.size() <= 0) {
                            System.out.println("Você ainda não adicionou nenhuma matrícula");
                        } else {
                            System.out.println("Digite o id da disciplina a qual deseja remover:");
                            for (Disciplina d : disciplinas) {
                                System.out.println(d.getId() + " - " + d.getNomeDisciplina());
                            }
                            int idDisciplinaParaExcluir = f.nextInt();
                            if (idDisciplinaParaExcluir < 0
                                    || idDisciplinaParaExcluir > disciplinasCadastradas.size()) {
                                System.out.println("ID inválido.");
                            } else {
                                for (int i = 0; i < disciplinas.size(); i++) {
                                    if (disciplinas.get(i).getId() == idDisciplinaParaExcluir) {
                                        disciplinas.remove(i);
                                    }
                                }
                                System.out.println("Disciplina removida com sucesso.");
                            }
                        }
                        break;
                    case 3:
                        if (disciplinas.size() > 0) {
                            aluno1.realizarMatricula(disciplinas);
                            x = -1;
                        } else {
                            System.out.println("Adicione alguma disciplina primeiro.");
                        }
                        break;
                    default:
                        System.out.println("Opção inválida.");
                        break;
                }
            }
            
            int y = 0;
            while (y != -1 && !aluno1.getIsEmptyDisciplinas()) {
                System.out.println("\n--------MENU DE OPÇÕES DE DISCIPLINAS--------");
                System.out.println("Digite 1 para ver as disciplinas.");
                System.out.println("Digite 2 para trancar uma disciplina.");
                System.out.println("Digite -1 para encerrar.");

                y = f.nextInt();

                switch (y) {
                    case -1:
                        System.out.println("Opções de acesso encerrado");
                        break;
                    case 1:
                        System.out.println("DISCIPLINAS: ");

                        for (Disciplina d : aluno1.getDisciplinas()) {
                            System.out.println(d);
                        }

                        break;
                    case 2:

                        System.out.println("Digite o id da disciplina a qual deseja trancar:");
                        for (Disciplina d : disciplinas) {
                            System.out.println(d.getId() + " - " + d.getNomeDisciplina());
                        }
                        int idDisciplinaParaTrancar = f.nextInt();
                        if (aluno1.trancarDisciplinaById(idDisciplinaParaTrancar)) {
                            System.out
                                    .println("DISCIPLINA DE ID: " + idDisciplinaParaTrancar + " TRANCADA COM SUCESSO");
                        } else {
                            System.out.println("FALHA AO TRANCAR DISCIPLINA");
                        }
                        break;
                    default:
                        System.out.println("Opção inválida.");
                        break;
                }
            }

            System.out.println(aluno1);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } finally {
            f.close();
        }
    }
}
