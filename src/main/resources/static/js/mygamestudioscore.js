function printScore() {
	var e = document.getElementById("selectGame");
	var game = e.options[e.selectedIndex].value;

		$.ajax({				
			type : "GET",
			url : "http://localhost:8080/rest/score/" + game,
			dataType : "json"

		}).done(
				function(receivedData) {
					$("#scoreTable").html(
							Mustache.render($("#tmplScores").html(), receivedData));
				}).fail(function() {
			$("#scoreTable").html("<p>Nothing to Display!.</p>");
		}

		);
	}




//$("#btSaveScore").click(
//		function(){
//			var index = document.getElementById("selectGame");
//			var game = index.options[index.selectedIndex].value;
//			var score = parseInt($("#inNewScore").val().trim());
//			var username = $("#userName").val();
//			console.log(score);
//			console.log(game);
//			console.log(userName);
//		
//			if(isNaN(score)){
//				window.alert("Bad input!");
//				return;
//			}
//			
//			var data2send = {
//					username: username,
//					game: game,
//					value: score
//			}
//			
//	
//						
//			$.ajax(
//					{
//						
//						type: "POST",
//						url: "http://localhost:8080/rest/score/",
//						contentType: "application/json",
//						data: JSON.stringify(data2send)
//						
//					}
//					)
//					.done (
//							function(receivedData){
//							drawScoreTable();
//								
//							}
//					)
//					.fail (
//							function(){
//								window.alert("Unable to send data!");
//							});
//		
//		}
//		
//		
//		);





$("#btSaveScore2").click(
		function(){
			console.log("bla");
			var score = parseInt($("#inNewScore").val().trim());
			console.log(score);
		
			if(isNaN(score)){
				window.alert("Bad input!");
				return;
			}
			
			var data2send = {
					username:"matus",
					game:"Mines",
					value: score
			}
			
			console.log(data2send);
		//	console.log(score);
			
			
			$.ajax(
					{
						
						type: "POST",
						url: "http://localhost:8080/rest/score/",
						contentType: "application/json",
						data: JSON.stringify(data2send)
						
					}
					)
					.done (
							function(receivedData){
							drawScoreTable();
								
							}
					)
					.fail (
							function(){
								window.alert("Unable to send data!");
							});
		
		}
		
		
		);

$("#btSaveScorej").click(function() {
	var index = document.getElementById("games");
	var game = index.options[index.selectedIndex].value;
	var score = parseInt($("#newScore").val().trim());
	var username = $("#newUsername").val();
	if (isNaN(score)) {
		window.alert("Bad Input!");
		return;
	}
	var data2send = {
		username : username,
		game : game,
		value : score
	}

	$.ajax({

		type : "POST",
		url : "http://localhost:8080/rest/score",
		contentType : "application/json",
		data : JSON.stringify(data2send)

	}).done(function(receivedData) {
		zobrazSkore();
		document.getElementById("newScore").value = "";
		document.getElementById("newUsername").value = "";
	}).fail(function() {
		window.alert("Unable to send data");
	}

	);

});

