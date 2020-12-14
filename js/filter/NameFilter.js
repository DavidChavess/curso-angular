angular.module("listaTelefonica").filter("name", () => {
    return (input) => {
        var nomeFormatado = input.split(" ")
            .map( (nome) => {
                if ( nome === "da" || nome === "de" ) {
                    return nome;
                }
                return nome.charAt(0).toUpperCase() + nome.substring(1).toLowerCase();
            }).join(" ");

        return nomeFormatado;
    }
})