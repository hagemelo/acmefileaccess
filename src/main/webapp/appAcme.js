angular.module("appAcme",[]);



angular.module("appAcme").controller("acmecontroller", function ($scope, $http) { 
	
	const dados = {
	  	arquivos :[],
	  	arquivosCarregados : false,
	  	statusRetorno : "",
	  	errobackendcarregararquivos : false,
	  	KeyFile : {
	  		originName : "",
	  		targetName : ""
	  	},
		file :[]
	};

	
	
	var carregarArquivios = function(){
		$http.get("http://localhost:8081/listdiretorios")
		.then(function (response) {
			
			dados.arquivos = [];
			dados.arquivos = response.data;
			dados.arquivosCarregados = true;
			$scope.dados = dados;
		}, function myError(response) {

			var status = response.status;
			if (status != 200){
				$scope.dados.errobackendcarregararquivos = true;
			}
			$scope.dados.statusRetorno = response.statusText;
			$scope.dados.pedidoscarregados = false;
		}
		);
	};

	carregarArquivios();
	
	
	$scope.executarrenomeararquivo = function(){
	
		$http.post("http://localhost:8081/renamearquivo", dados.KeyFile).then(function (response) {
				
			var mensagem = "Arquivo '" + KeyFile.originName + "' renomeado para '" +  KeyFile.targetName + " com Sucesso!";
			carregarArquivios();
			
		});

	};
	
	$scope.executaruoloadfile = function(){
		
		var formData = new FormData();
        formData.append('file', file.files[0]);
        		
		$http.post("http://localhost:8081/uploadarquivo", formData, {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        }).then(function (response) {
				
			var mensagem = "Arquivo '" + KeyFile.originName + "' renomeado para '" +  KeyFile.targetName + " com Sucesso!";
			carregarArquivios();
			
		});

	};
	
	$scope.renomeararquivo = function(){

		return !dados.arquivosCarregados && dados.errobackendcarregararquivos;
	};
	
	
	$scope.apresentarMsgNaoHaArquivos = function(){

		return !dados.arquivosCarregados && dados.errobackendcarregararquivos;
	};
	
	$scope.apresentarMsgErroCarregarArquivos = function(){

		return dados.errobackendcarregararquivos;
	};
	
	$scope.apresentarTabela = function(){

		return dados.arquivosCarregados && !dados.errobackendcarregararquivos;
	};
	
	$scope.selecionarArquivo = function(arquivo){

		$scope.dados.KeyFile.originName = arquivo
	};

	
});



