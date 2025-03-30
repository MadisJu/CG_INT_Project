document.getElementById('fetchButton').addEventListener('click', async function()
{
    event.preventDefault();
    const apiUrl = 'http://localhost:8080/api/flights';

    try 
    {
        //Andmed filtrist
        const startdata = document.getElementById('start').value.trim();
        const destinationdata = document.getElementById('destination').value.trim();
        const datedata = document.getElementById('date').value.trim();
        const priceMaxdata = document.getElementById('price_max').value.trim();

        //Parameetrite loomine query jaoks
        let parameters = "";
        parameters += startdata ? `start=${startdata}&` : "";
        parameters += destinationdata ? `destination=${destinationdata}&` : "";
        parameters += datedata ? `date=${datedata}&` : "";
        parameters += priceMaxdata ? `price_max=${priceMaxdata}` : "";

        //Urli kokku panemine ja requestimine
        let filledUrl = apiUrl + "?" + parameters;
        const response = await fetch(filledUrl);
        const jsonData = await response.json();

        

        const container = document.getElementById('container');
        container.innerHTML = '';

        jsonData.forEach((item) => 
        {
            const div = document.createElement('div');
            div.className = 'object';

            const flight = document.createElement('h2');
            flight.className = 'flight';
            flight.textContent = `${item.algkoht} -> ${item.sihtkoht}`;

            const timediv = document.createElement('div');
            timediv.className = 'time-container';
            
            const departure = document.createElement('h3');
            departure.textContent = `Departure: ${item.lahkumisetund + ":" + item.lahkumiseminutid}`;
            
            const time = document.createElement('h3');
            time.textContent = `Duration: ${item.lennuaeg} h`;
            
            const date = document.createElement('h3');
            date.textContent = `Date: ${item.kuupäev}`;

            const pricediv = document.createElement('div');
            pricediv.className = 'price-container';

            const select = document.createElement('button');
            select.className = 'select-button';
            select.textContent = 'Select';
                        
            const price = document.createElement('h2');
            price.textContent = `Price: ${item.hind} €`;


            container.appendChild(div);
            div.appendChild(flight);
            div.appendChild(timediv)
            timediv.appendChild(departure);
            timediv.appendChild(time);
            div.appendChild(date);
            div.appendChild(pricediv);
            pricediv.appendChild(select);
            pricediv.appendChild(price);

        });
    } 
    catch (error) 
    {
        console.error('Error:', error);
    }
});