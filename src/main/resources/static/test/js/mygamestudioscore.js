function printScore() {
	var e = document.getElementById("selectGame");
	var game = e.options[e.selectedIndex].value;
	console.log(game);
		$.ajax({				
			type : "GET",
			url : "http://localhost:8080/rest/score/" + game,
			dataType : "json"

		}).done(
				function(receivedData) {
					$("#scoreTable").html(
							Mustache.render($("#tmplScores").html(), receivedData));
				}).fail(function() {
			$("#scoreTable").html("<p>SORRY.</p>");
		}

		);
	}



$("#btSaveScore").click(
function(){
	var index = document.getElementById("selectGame");
	var game = index.options[index.selectedIndex].value;
	var score = parseInt($("#inNewScore").val().trim());
	var username = $("#userName").val();
	console.log(score);
	console.log(game);
	console.log(userName);

	if(isNaN(score)){
		window.alert("Bad input!");
		return;
	}
	
	var data2send = {
			username: username,
			game: game,
			value: score
	}
	

				
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
	
	printScore();
}


);