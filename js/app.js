angular.module("listaTelefonica", []);
angular.module("listaTelefonica").controller("ListaTelefonicaController", ($scope, $http)=>{
    $scope.title = "Lista telefônica";
    $scope.contatos = [];
    $scope.operadoras = [
        { nome : "TIM"},
        { nome : "VIVO"},
        { nome : "OI"},
        { nome : "GNV"},
        { nome : "EMBRATEL"}
    ];

    function carregarContatos(){
        $http.get("http://localhost:8080/api/contatos")
        .then(res => {
            $scope.contatos = res.data;
        })
        .catch(err => {
            console.log(err)
        })
    }

    $scope.add = (contato) => {
        contato.operadora = contato.nomeOperadora.nome;

        $http.post("http://localhost:8080/api/contatos", contato)
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
            $http.delete(`http://localhost:8080/api/contatos/${c.id}`)
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