import java.time.LocalDate;

public class Funcionario {
    private int idFunc;
    private String nomeFunc;
    private String departamento;
    private LocalDate dataContratacao = LocalDate.now();
    private double salario;
    private String documento;
    private boolean estaAtivo;

    public void setIdFunc(int idFunc){
        this.idFunc = idFunc;
    }

    public int getIdFunc(){
        return idFunc;
    }

    public void setNomeFunc(String nomeFunc){
        this.nomeFunc = nomeFunc;
    }

    public String getNomeFunc(){
        return nomeFunc;
    }

    public void setDepartamento(String departamento){
        this.departamento = departamento;
    }

    public String getDepartamento(){
        return departamento;
    }

    public void setDataContratacao(LocalDate dataContratacao){
        this.dataContratacao = dataContratacao;
    }

    public LocalDate getDataContratacao(){
        return dataContratacao;
    }
    public void setSalario(double salario){
        this.salario = salario;
    }

    public double getSalario(){
        return salario;
    }

    public void setDocumento(String documento){
        this.documento = documento;
    }

    public String getDocumento(){
        return documento;
    }

    public void setEstaAtivo(boolean estaAtivo){
        this.estaAtivo = estaAtivo;
    }

    public boolean getEstaAtivo(){
        return estaAtivo;
    }

    void atualizarSalario(double valor){
        this.salario += valor;
    }

    void demitirFuncionario(){
        this.estaAtivo = false;
    }

    void imprimir(){
        System.out.println();
        System.out.println("Dados do Funcionário:");
        System.out.println("id: " + idFunc);
        System.out.println("nome: " + nomeFunc);
        System.out.println("departamento: " + departamento);
        System.out.println("data de contratacao: " + dataContratacao);
        System.out.println("salário: " + salario);
        System.out.println("documento: " + documento);
        if(estaAtivo){
            System.out.println("Funcionário está ativo");
        }else {
            System.out.println("Funcionário está inativo");
        }
        System.out.println("________________________________");
    }

}
