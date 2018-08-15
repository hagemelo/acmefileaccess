angular.module("appAcme",[]);



angular.module("appAcme").controller("acmecontroller", function ($scope, $http) { 
	
	const dados = {
	  	arquivos :[],
	  	curPage : 0,
	  	pageSize : 10,
	  	arquivosCarregados : false,
	  	statusRetorno : "",
	  	errobackendcarregararquivos : false,
	  	apresentarErro : false,
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
	
		$http.post("http://localhost:8081/renamearquivo", dados.KeyFile)
		.then(function (response) {
				
			$scope.dados.mensagemrespostadeacao = response.data.mensagem;
			$scope.dados.showrespostadeacao = true;
			carregarArquivios();
		}, function myError(response) {
			
			$scope.dados.mensagemrespostadeacao = response.data.mensagem;
			$scope.dados.apresentarErro = true;
		}
		
		
		);

	};
	
	$scope.executarduplicararquivo = function(){
		
		$http.post("http://localhost:8081/duplicararquivo", dados.KeyFile)
		.then(function (response) {
				
			$scope.dados.mensagemrespostadeacao = response.data.mensagem;
			$scope.dados.showrespostadeacao = true;
			carregarArquivios();
		}, function myError(response) {
			
			$scope.dados.mensagemrespostadeacao = response.data.mensagem;
			$scope.dados.apresentarErro = true;
		}
		
		
		);

	};
	
	$scope.executardeletararquivo = function(){
		
		$http.delete("http://localhost:8081/deletararquivo", dados.KeyFile)
		.then(function (response) {
				
			$scope.dados.mensagemrespostadeacao = response.data.mensagem;
			$scope.dados.showrespostadeacao = true;
			carregarArquivios();
		}, function myError(response) {
			
			$scope.dados.mensagemrespostadeacao = response.data.mensagem;
			$scope.dados.apresentarErro = true;
		}
		);

	};
	
	$scope.executaruoloadfile = function(){
		
		var formData = new FormData();
        formData.append('file', file.files[0]);
        		
		$http.post("http://localhost:8081/uploadarquivo", formData, {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        }).then(function (response) {
				
			$scope.dados.mensagemrespostadeacao = response.data.mensagem;
			$scope.dados.showrespostadeacao = true;
			carregarArquivios();
			
		});

	};
	
	$scope.renomeararquivo = () => !$scope.dados.arquivosCarregados && $scope.dados.errobackendcarregararquivos;
	
	
	$scope.apresentarMsgNaoHaArquivos = () =>!$scope.dados.arquivosCarregados && $scope.dados.errobackendcarregararquivos;
	
	$scope.apresentarMsgErroCarregarArquivos = () => $scope.dados.errobackendcarregararquivos;
	
	$scope.apresentarTabela = () => dados.arquivosCarregados && !$scope.dados.errobackendcarregararquivos;
	
	$scope.fecharMsgRespostaAcao= () => $scope.dados.showrespostadeacao = false;
	
	$scope.apresentarMsgErro = () => $scope.dados.apresentarErro;
	
	$scope.numberOfPages =() => Math.ceil($scope.dados.arquivos.length / $scope.dados.pageSize)
	
	$scope.selecionarArquivo = (arquivo) =>{

		$scope.dados.KeyFile.originName = arquivo
	};

	
});


angular.module("appAcme").filter("pagination", function()
		{
		 return function(input, start)
		 {
		  start = +start;
		  return input.slice(start);
		 };
		});



