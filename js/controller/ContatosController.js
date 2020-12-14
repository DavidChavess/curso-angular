angular.module("listaTelefonica", []);
angular.module("listaTelefonica").controller("ListaTelefonicaController", ($scope, apiService )=>{
    $scope.title = "Lista telefônica";
    $scope.contatos = [];
    $scope.operadoras = OperadoraService.getOperadoras();

    function carregarContatos(){
        
        apiService.getContatos().then(res => {
            $scope.contatos = res.data;
        })
        .catch(err => {
            console.log(err)
        })
    }

    $scope.add = (contato) => {
        contato.operadora = contato.nomeOperadora.nome;

        apiService.saveContato(contato)
        .then(res => {
            $scope.contatos.push(res.data);
            delete $scope.contato;
            $scope.contatosForm.$setPristine();
        })
        .catch(err => {
            console.log(err)
        })      

    }

    $scope.apagar = (contato) => {
        contato.filter(c => c.selecionado).forEach(c => {
            apiService.deleteContato(c.id)
            .then(res => {
               carregarContatos();
            })
            .catch(err => {
                console.log(err)
            })      
        });
    }

    $scope.ordenarPor = (ordenacao) => {
        $scope.criterio = ordenacao;
        $scope.ordem = !$scope.ordem;  
    }

    carregarContatos();
})