import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GerenciarFuncionario {

    public static List<Empresa> empresa = new ArrayList<>();
    public static List<Funcionario> quadro = new ArrayList<>();
    public static Funcionario funcio;
    public static Empresa emp = new Empresa();
    public static Scanner scanner = new Scanner(System.in).useDelimiter("\n");

    public static String pausa;

    public static void main(String[] args) {

        int op = 0;

        while (op != 9) {
            System.out.println("----------------------------------------------------");
            System.out.println("Selecione a opção desejada");
            System.out.println("1 - Cadastrar Funcionário");
            System.out.println("2 - Cadastrar Empresa");
            System.out.println("3 - Atualizar Salário");
            System.out.println("4 - Demitir funcionário");
            System.out.println("5 - Listar funcionários de uma empresa");
            System.out.println("6 - Listar funcionários de todas as empresas");
            System.out.println("7 - Adicionar funcionário ao quadro da empresa");
            System.out.println("8 - Verificar existência de funcionário na empresa");
            System.out.println("9 - Sair");
            System.out.println("----------------------------------------------------");
            op = Integer.parseInt(scanner.nextLine());

            switch (op) {
                case 1:
                    cadastrarFunc();
                    continue;
                case 2:
                    cadastrarEmpresa();
                    continue;
                case 3:
                    atualizarSalario();
                    continue;
                case 4:
                    demitirFuncionario();
                    continue;
                case 5:
                    listFuncUmaEmpresa();
                    continue;
                case 6:
                    listFuncTodasEmpresas();
                    continue;
                case 7:
                    AdcFuncQuadro();
                    continue;
                case 8:
                    verificarExistenciaFunc();
                    continue;
                default:
                    System.exit(0);
                    break;
            }
        }

    }

    private static void cadastrarFunc() {
        try {
            int idEmpresa = 0;
            if (empresa.isEmpty()) {
                System.out.println("Cadastre uma empresa antes!");
                scanner.nextLine();
                System.out.println();
                return;
            }
            System.out.println("Digite o código da empresa a qual esse funcionário fará parte: ");
            idEmpresa =  Integer.parseInt(scanner.nextLine());;

            if (!testaEmpresa(idEmpresa)) {
                System.out.println("");
                System.out.println("Empresa não existe, tente novamente!");
                System.out.println("");
                scanner.nextLine();
                System.out.println();
                cadastrarFunc();
            }

            funcio = new Funcionario();
            System.out.println("Digite o código do funcionário: ");
            funcio.setIdFunc( Integer.parseInt(scanner.nextLine()));
            System.out.println("Digite o nome do funcionário: ");
            funcio.setNomeFunc(scanner.nextLine());
            System.out.println("Digite o departamento do funcionário: ");
            funcio.setDepartamento(scanner.nextLine());
            System.out.println("Digite o documento do funcionário: ");
            funcio.setDocumento(scanner.nextLine());
            System.out.println("Digite o salario do funcionário: ");
            funcio.setSalario(Double.parseDouble(scanner.nextLine()));
            funcio.setDataContratacao(LocalDate.now());
            funcio.setEstaAtivo(true);

            for (Empresa empr : empresa) {
                if (empr.getIdEmpresa() == idEmpresa) {
                    empr.adicionarEmpregados(funcio);
                }
            }
            System.out.println("Funcionário inserido com sucesso! ");
            System.out.println("--------------------------------------------------------");
            System.out.println("Pressione Enter para continuar!");
            scanner.nextLine();
            System.out.println();
        } catch (Exception erro) {
            System.out.println("--------------------------------------------------------");
            System.out.println("Ocorreu um erro: ");
            System.out.println(erro);
            System.out.println("--------------------------------------------------------");
            scanner.nextLine();
            System.out.println();
        }

    }

    private static void cadastrarEmpresa() {
        try {
            emp = new Empresa();
            System.out.println("Digite o código da empresa: ");
            emp.setIdEmpresa(Integer.parseInt(scanner.nextLine()));

            if (testaEmpresa(emp.getIdEmpresa())) {
                System.out.println("");
                System.out.println("Empresa já existe, tente novamente!");
                System.out.println("");
                scanner.nextLine();
                System.out.println();
                cadastrarEmpresa();
            }

            System.out.println("Digite a razão social da empresa: ");
            emp.setRazaoSocial(scanner.nextLine());
            System.out.println("Digite o CNPJ da empresa: ");
            emp.setCnpj(Integer.parseInt(scanner.nextLine()));

            empresa.add(emp);
            System.out.println();
            System.out.println("Empresa Cadastrada com sucesso!");
            System.out.println("--------------------------------------------------------");
            System.out.println("Pressione Enter para continuar!");
            scanner.nextLine();
        } catch (Exception erro) {
            System.out.println("--------------------------------------------------------");
            System.out.println("Ocorreu um erro: ");
            System.out.println(erro);
            System.out.println("--------------------------------------------------------");
            scanner.nextLine();
            System.out.println();
        }
    }

    private static void atualizarSalario() {
        try {
            int idFunc, idEmpre;
            double valor;
            System.out.println();
            System.out.println("Digite o código empresa do funcionário: ");
            idEmpre =  Integer.parseInt(scanner.nextLine());;
            System.out.println("Digite o código do funcionário: ");
            idFunc =  Integer.parseInt(scanner.nextLine());;
            System.out.println("Digite o valor a ser adicionado: ");
            valor = Double.parseDouble(scanner.nextLine());

            for (Empresa empr : empresa) {
                if (empr.getIdEmpresa() == idEmpre) {
                    for (Funcionario func : empr.getEmpregados()) {
                        if (func.getIdFunc() == idFunc)
                            func.atualizarSalario(valor);
                    }
                    break;
                }
            }

            System.out.println();
            System.out.println("Salário atualizado com sucesso!");
            System.out.println("--------------------------------------------------------");
            System.out.println("Pressione Enter para continuar!");
            scanner.nextLine();
            System.out.println();

        } catch (Exception erro) {
            System.out.println("--------------------------------------------------------");
            System.out.println("Ocorreu um erro: ");
            System.out.println(erro);
            System.out.println("--------------------------------------------------------");
            scanner.nextLine();
            System.out.println();
        }
    }

    private static void demitirFuncionario() {
        try {
            int idFunc, idEmpre;
            System.out.println();
            System.out.println("Digite o código empresa do funcionário: ");
            idEmpre =  Integer.parseInt(scanner.nextLine());;
            System.out.println("Digite o código do funcionário: ");
            idFunc =  Integer.parseInt(scanner.nextLine());;

            for (Empresa empr : empresa) {
                if (empr.getIdEmpresa() == idEmpre) {
                    for (Funcionario func : empr.getEmpregados()) {
                        if (func.getIdFunc() == idFunc)
                            func.demitirFuncionario();
                    }
                    break;
                }
            }
            System.out.println("--------------------------------------------------------");
            System.out.println("Pressione Enter para continuar!");
            scanner.nextLine();
            System.out.println();

        } catch (Exception erro) {
            System.out.println("--------------------------------------------------------");
            System.out.println("Ocorreu um erro: ");
            System.out.println(erro);
            System.out.println("--------------------------------------------------------");
            scanner.nextLine();
            System.out.println();
        }
    }

    private static void listFuncUmaEmpresa() {
        try {
            int id;
            System.out.println("Digite o código da empresa: ");
            id = ( Integer.parseInt(scanner.nextLine()));
            if (testaEmpresa(id) == false) {
                System.out.println("Essa empresa não existe!: ");
                scanner.nextLine();
                System.out.println();
                return;
            }

            for (Empresa empr : empresa) {
                if (empr.getIdEmpresa() == id) {
                    empr.mostrarEmpregados();
                    break;
                }
            }
            System.out.println("--------------------------------------------------------");
            System.out.println("Pressione Enter para continuar!");
            scanner.nextLine();
            System.out.println();
        } catch (Exception erro) {
            System.out.println("--------------------------------------------------------");
            System.out.println("Ocorreu um erro: ");
            System.out.println(erro);
            System.out.println("--------------------------------------------------------");
            scanner.nextLine();
            System.out.println();
        }
    }

    private static void listFuncTodasEmpresas() {
        try {
            for (Empresa empr : empresa) {
                System.out.println(empr.getRazaoSocial());
                System.out.println("------------------------------------------");
                empr.mostrarEmpregados();
            }
            System.out.println("--------------------------------------------------------");
            System.out.println("Pressione Enter para continuar!");
             scanner.nextLine();
             System.out.println();
        } catch (Exception erro) {
            System.out.println("--------------------------------------------------------");
            System.out.println("Ocorreu um erro: ");
            System.out.println(erro);
            System.out.println("--------------------------------------------------------");
            scanner.nextLine();
            System.out.println();
        }
    }

    private static void AdcFuncQuadro() {
        try {
            int idFunc;
            boolean controle = false;
            funcio = new Funcionario();
            System.out.println("Digite o código do funcionário: ");
            idFunc =  Integer.parseInt(scanner.nextLine());;
            for (Empresa empr : empresa) {
                for (Funcionario func : empr.getEmpregados()) {
                    if (idFunc == func.getIdFunc()) {
                        quadro.add(func);
                        controle = true;
                    }
                }
            }
            if (controle) {
                System.out.println("Funcionario adicionado com sucesso!");

            } else {
                System.out.println("Não existe nenhum funcionario com este ID em nenhuma empresa!");

            }
            scanner.nextLine();
            System.out.println();


        } catch (Exception erro) {
            System.out.println("--------------------------------------------------------");
            System.out.println("Ocorreu um erro: ");
            System.out.println(erro);
            System.out.println("--------------------------------------------------------");
            scanner.nextLine();
            System.out.println();
        }
    }

    private static void verificarExistenciaFunc() {
        try {
            String nomeFunc;
            int idEmpresa, opc = 0, codigo;

            System.out.println("Digite o codigo da empresa: ");
            idEmpresa =  Integer.parseInt(scanner.nextLine());;

            if (!testaEmpresa(idEmpresa)) {
                System.out.println("Empresa Inexistente! ");
                 scanner.nextLine();
                 System.out.println();
                verificarExistenciaFunc();
            }

            while (opc != 1 && opc != 2) {
                System.out.println("1 - Nome | 2 - Código: ");
                opc =  Integer.parseInt(scanner.nextLine());;
            }

            switch (opc) {
                case 1:
                    System.out.println("Digite o nome do funcionario: ");
                    nomeFunc = scanner.nextLine();
                    for (Empresa empr : empresa) {
                        if (empr.getIdEmpresa() == idEmpresa) {
                            if (empr.contemFuncionario(nomeFunc))
                                System.out.println("Funcionário existe!");
                            else
                                System.out.println("Funcionário não existe!");
                        }
                    }
                    break;
                case 2:
                    System.out.println("Digite o Código do funcionario: ");
                    codigo =  Integer.parseInt(scanner.nextLine());;
                    for (Empresa empr : empresa) {
                        if (empr.getIdEmpresa() == idEmpresa) {
                            if (empr.contemFuncionario(codigo))
                                System.out.println("Funcionário existe!");
                            else
                                System.out.println("Funcionário não existe!");
                        }
                    }
                    break;
                default:
                    System.out.println("Opção inexistente!");
            }
            System.out.println("--------------------------------------------------------");
            System.out.println("Pressione Enter para continuar!");
             scanner.nextLine();
            System.out.println();


        } catch (Exception erro) {
            System.out.println("--------------------------------------------------------");
            System.out.println("Ocorreu um erro: ");
            System.out.println(erro);
            System.out.println("Pressione Enter para continuar!");
            System.out.println("--------------------------------------------------------");
             scanner.nextLine();
            System.out.println();

        }
    }

    private static boolean testaEmpresa(int idEmpresa) {
        try {
            boolean funcionou = false;
            for (Empresa empr : empresa) {
                if (empr.getIdEmpresa() == idEmpresa) {
                    funcionou = true;
                    break;
                }
            }
            return funcionou;
        } catch (Exception erro) {
            throw erro;
        }
    }

}
