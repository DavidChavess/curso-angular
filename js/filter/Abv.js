angular.module("listaTelefonica").filter("abv", () => {
    return (input, size) => {

        if(input.length <= size) {
            return input;
        }            
        
        return input.substring(0, (size || 2) ).concat("...");
    }
})