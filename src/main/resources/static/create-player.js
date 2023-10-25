console.log("TEST");
document.getElementById('createPlayerForm').addEventListener('submit', function(event) {
  
    event.preventDefault();
    
    const username = document.getElementById('username').value;
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;

    const jsonData = JSON.stringify({ username, email, password });
    console.log('JSON data being sent:', jsonData);

    
    // Make an API call to the backend to create the player
    fetch('http://localhost:8080/games/api/create_new_player', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: jsonData,
    })
    .then(response => response.json())
    .then(data => {
      console.log('Player created:', data);
      //Redirect user to home page login
      window.location.replace('http://localhost:8080/');
    })
    .catch((error) => {
      console.error('Error creating player:', error);
      //redirect back to player form again
      window.location.replace('http://localhost:8080/player-ui/player-creation-form80/');
    });
  });
  