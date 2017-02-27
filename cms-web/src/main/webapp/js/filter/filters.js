tcApp.filter('sumOfItems',function(){
	return function(data,key){
		if(typeof(data)=="undefined"||typeof(key)=="undefined"){
			return 0;
		}
		var sum = 0,i = data.length-1;
		for(;i>=0;i--){
			sum += parseFloat(data[i][key]);
		}
		return sum;
	}
});
