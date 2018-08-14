angular.module("appAcme",[]);



angular.module("appAcme").controller("acmecontroller", function ($scope, $http) { 
	
	const dados = {
	  	arquivos :[],
	  	arquivosCarregados : false,
	  	statusRetorno : "",
	  	errobackendcarregararquivos : false,
	  	showrespostadeacao: false,
		mensagemrespostadeacao: "",
	  	KeyFile : {
	  		originName : "",
	  		targetName : ""
	  	},
		file :[]
	};

	$scope.dados = dados;
	
	var carregarArquivios = function(){
		$http.get("http://localhost:8081/listdiretorios")
		.then(function (response) {
			
			
			$scope.dados.arquivos = response.data;
			$scope.dados.arquivosCarregados = true;
			
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
				
			$scope.dados.mensagemrespostadeacao = "Arquivo '"+dados.KeyFile.originName+"' renomeado para '"+dados.KeyFile.targetName+"' com Sucesso!";
			$scope.dados.showrespostadeacao = true;
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
				
			$scope.dados.mensagemrespostadeacao = "Arquivo '" + dados.KeyFile.originName +"' renomeado para '"+dados.KeyFile.targetName+"' com Sucesso!";
			$scope.dados.showrespostadeacao = true;
			carregarArquivios();
			
		});

	};
	
	$scope.renomeararquivo = () => !$scope.dados.arquivosCarregados && $scope.dados.errobackendcarregararquivos;
	
	
	$scope.apresentarMsgNaoHaArquivos = () =>!$scope.dados.arquivosCarregados && $scope.dados.errobackendcarregararquivos;
	
	$scope.apresentarMsgErroCarregarArquivos = () => $scope.dados.errobackendcarregararquivos;
	
	$scope.apresentarTabela = () => dados.arquivosCarregados && !$scope.dados.errobackendcarregararquivos;
	
	$scope.fecharMsgRespostaAcao= () => $scope.dados.showrespostadeacao = false;
	
	$scope.selecionarArquivo = (arquivo) =>{

		$scope.dados.KeyFile.originName = arquivo
	};

	
});



