document.getElementById('fetchButton').addEventListener('click', async function() 
{
    const apiUrl = 'http://localhost:8080/api/flights';

    try 
    {
        const response = await fetch(apiUrl);
        const jsonData = await response.json();

        const container = document.getElementById('container');
        container.innerHTML = '';

        jsonData.forEach((item) => 
        {
            const div = document.createElement('div');
            div.className = 'object';
            div.textContent = `Hind: ${item.hind}`;
            container.appendChild(div);
        });
    } 
    catch (error) 
    {
        console.error('Error:', error);
    }
});