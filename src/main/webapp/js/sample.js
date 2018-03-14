var app = angular.module('app', []);

app.controller('Controller1', function($scope, shareDataService) {
    $scope.someData="i am from controoler 1";
    
        shareDataService.addList($scope.someData);
  
});

app.controller('Controller2', function($scope, shareDataService) {
    $scope.lists = shareDataService.getList();
});


app.service('shareDataService', function() {
  var myList = [];

  var addList = function(newObj) {
      myList.push(newObj);
  }

  var getList = function(){
      return myList;
  }

  return {
    addList: addList,
    getList: getList
  };

});