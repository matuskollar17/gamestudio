drawScoreTable();

function drawScoreTable() {

$.ajax(
		{
			
			type: "GET",
			url: "http://localhost:8080/rest/score/Mines",
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
					username:"Anonymous",
					game:"Mines",
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
		
		}
		
		
		);