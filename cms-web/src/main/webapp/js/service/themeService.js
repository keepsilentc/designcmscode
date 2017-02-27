tcApp.service("themeService",function($http){
		this.getAllTheme = function(param){
			return $http.post(ctx+"/theme/getAllTheme.do",param);
		};
		this.getThemes = function(vo){
			return $http.post(ctx+"/theme/search.do",vo);
		};
		this.addTheme = function(theme){
			return $http.post(ctx+"/theme/add.do",theme);
		}
		this.updateTheme = function(theme){
			return $http.post(ctx+"/theme/update.do",theme);
		}
		this.delTheme = function(id){
			return $http.post(ctx+"/theme/del.do",id);
		}
});