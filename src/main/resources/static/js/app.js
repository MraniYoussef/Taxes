/**
 * 
 */
var app=angular.module('myApp',['ui.router']);

app.config([
	'$stateProvider',
	'$urlRouterProvider',
	function($stateProvider,$urlRouterProvider){
	
	$stateProvider.state("entreprises",{
		url:"/entreprises", 
		templateUrl:"views/entreprises.html", 
		controller: "MyController"
	});
	$stateProvider.state("taxes",{
		url:"/taxes", 
		templateUrl:"views/taxes.html", 
		controller: "TaxeController"
	});
	
  $urlRouterProvider.otherwise('entreprises');
}]);
app.controller('TaxeController',function(){
	
});
app.controller('MyController', function($scope,$http){
	$scope.pageEntreprises=[];
	$scope.motCle="";
	$scope.pageCourante=0;
	$scope.size=5;
	$scope.pages=[];
	$scope.chercher=function(){
		$scope.pageEntreprises = $http.get("http://localhost:8080/listEntreprises?motCle="
		+$scope.motCle+"&page="+$scope.pageCourante+"&size="+$scope.size).then(function (data) {
        $scope.pageEntreprises = genericSuccess(data);
		$scope.pages=new Array(data.data.totalPages);
    });
    var genericSuccess=function(res) {
        return res.data;
    }
	
		
	}
	
	/*$http.get("http://localhost:8080/listEntreprises?page=1&size=3")
	.success(function(data){
		$scope.pageEntreprises=data;
	}).error(function(err){
		Console.log(err);
	});*/
	
	$scope.goToPage=function(p){
		
		$scope.pageCourante=p;
		$scope.chercher();
	}
	
	
});