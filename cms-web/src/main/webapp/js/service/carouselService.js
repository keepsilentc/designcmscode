tcApp.service("carouselService",function($http){
		this.getAllCarousel = function(vo){
			return $http.post(ctx+"/carousel/search.do",vo);
		};
		this.addCarousel = function(carousel){
			return $http.post(ctx+"/carousel/add.do",carousel);
		};
		this.updateCarousel = function(carousel){
			return $http.post(ctx+"/carousel/update.do",carousel);
		};
		this.delCarousel = function(carousel){
			return $http.post(ctx+"/carousel/del.do",carousel);
		};
});