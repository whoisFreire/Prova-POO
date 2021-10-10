import java.util.ArrayList;
import java.util.List;

public class Empresa {
    private int idEmpresa;
    private String razaoSocial;
    private int cnpj;
    private List<Funcionario> empregados = new ArrayList<Funcionario>();

    public void setIdEmpresa(int idEmpresa){
        this.idEmpresa = idEmpresa;
    }

    public int getIdEmpresa(){
        return idEmpresa;
    }

    public void setRazaoSocial(String razaoSocial){
        this.razaoSocial = razaoSocial;
    }

    public String getRazaoSocial(){
        return razaoSocial;
    }

    public void setCnpj(int cnpj){
        this.cnpj = cnpj;
    }

    public int getCnpj(){
        return cnpj;
    }

    public List<Funcionario> getEmpregados() {
        return empregados;
    }

    void adicionarEmpregados(Funcionario funcionario){
        this.empregados.add(funcionario);
    }

    void mostrarEmpregados(){
        if(this.empregados.size() > 0){
            this.empregados.forEach((funcionario) -> funcionario.imprimir());
        }else {
            System.out.println("Não existem funcionários cadastrados!");
        }
    }


    public boolean contemFuncionario(int idFuncio){
        for (Funcionario reg : empregados) {
            if(reg.getIdFunc() == idFuncio)
                return true;
        }
        return false;

    }
    public boolean contemFuncionario(String nomeFuncio){
        for (Funcionario reg : empregados) {
            if(reg.getNomeFunc().equals(nomeFuncio))
                return true;
        }
        return false;

    }

    void inativarFuncionario(int idFuncionario){
        this.empregados.forEach(funcionario -> {
            if(funcionario.getIdFunc() == idFuncionario){
                funcionario.demitirFuncionario();
            }
        });
    }

    void mostrarFuncionariosAtivos(){
        long quantidadeAtivos = empregados
            .stream()
            .filter(Funcionario::getEstaAtivo)
            .peek(Funcionario::imprimir)
            .count();
        System.out.println(quantidadeAtivos);
    }
}
