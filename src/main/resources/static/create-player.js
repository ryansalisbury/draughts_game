document.getElementById('createPlayerForm').addEventListener('submit', function(event) {
    event.preventDefault();
    
    const username = document.getElementById('username').value;
    const avatar = document.getElementById('avatar').value;
    
    // Make an API call to the backend to create the player
    fetch('http://your-backend-api-endpoint/players', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ username, avatar }),
    })
    .then(response => response.json())
    .then(data => {
      console.log('Player created:', data);
    })
    .catch((error) => {
      console.error('Error creating player:', error);
    });
  });
  