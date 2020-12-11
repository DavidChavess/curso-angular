angular.module("listaTelefonica", []);
angular.module("listaTelefonica").controller("ListaTelefonicaController", ($scope)=>{
    $scope.title = "Lista telefônica";
    $scope.contatos = [
        {nome : "David", telefone : "99760-9338", cor : "blue", data : new Date(), op : { nome : "Tim",  codigo : 41, categoria : "celular"}},
        {nome : "Fulano", telefone : "99760-9338", cor : "yellow", data : new Date(), op : { nome : "Oi",   codigo : 17, categoria : "celular"}},
        {nome : "Ciclano", telefone : "99760-9338", cor : "red", data : new Date(), op : { nome : "GNV",  codigo : 21, categoria : "Telefone"}}
    ];

    $scope.operadoras = [
        { nome : "Tim",  codigo : 41, categoria : "celular"},
        { nome : "Vivo", codigo : 15, categoria : "celular"},
        { nome : "Oi",   codigo : 17, categoria : "celular"},
        { nome : "GNV",  codigo : 21, categoria : "Telefone"},
    ];

    $scope.add = (contato) => {
        contato.data = new Date();
        $scope.contatos.push(contato);

        delete $scope.contato;
        $scope.contatosForm.$setPristine();
    }

    $scope.apagar = (contato) => {
        $scope.contatos = contato.filter(c => !c.selecionado);
    }
    $scope.ordenarPor = (ordenacao) => {
        $scope.criterio = ordenacao;
        $scope.ordem = !$scope.ordem;  
    }
})