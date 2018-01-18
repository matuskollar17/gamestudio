

function drawScoreTable() {

$.ajax(
		{
			
			type: "GET",
			url: "http://localhost:8080/rest/score/Mines"  ,
			dataType: "json"
			
		}
		)
		.done (
				function(receivedData){
					console.log(receivedData);
					
					var template = $("#tmplScores").html();
					var html = Mustache.render(template, receivedData);
					console.log(html);
					$("#scoreTable").html(html);
					
				}
		)
		.fail (
				function(){
					$("#scoreTable").html("<p>Sorry, unable to get data.</p>");
				});
//);
}




$("#btSaveScore").click(
		function(){
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
			
			//console.log(data2send);
			console.log(score);
			
			
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




