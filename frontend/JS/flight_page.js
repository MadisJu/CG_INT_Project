
async function displayData(id) 
{
    const apiUrl = 'http://localhost:8080/api/flight?ID=' + id;
    const response = await fetch(apiUrl);
    const data = await response.json();

    document.getElementById("route-placeholder").textContent = `${data.algkoht} -> ${data.sihtkoht}`;
    document.getElementById("departure-placeholder").textContent = `Departure: ${data.lahkumisetund}:${data.lahkumiseminutid}`;
    document.getElementById("duration-placeholder").textContent = `Duration: ${data.lennuaeg} h`;
    document.getElementById("date-placeholder").textContent = `Date: ${data.kuupäev}`;
    document.getElementById("price-placeholder").textContent = `Price: ${data.hind} €`;
}

async function generateSeatingPlan(rows, columns, id) 
{
    const apiUrl = 'http://localhost:8080/api/seats?ID=' + id;
    const response = await fetch(apiUrl);
    const data = await response.json();

    const seatingContainer = document.getElementById("seating-container");
    seatingContainer.innerHTML = "";

    let eID = 1;

    for (let row = 1; row <= rows; row++) 
    {
        for (let col = 1; col <= columns; col++) 
        {
            const seat = document.createElement('div');
            let type = data[eID-1].seatType;
            console.log(type);
            let taken = data[eID-1].taken;
            if(taken === true)
            {
                seat.className = 'seat-taken'
            }
            else if(type === 3)
            {
                seat.className = 'seat-first'
            }
            else if(type === 2)
            {
                seat.className = 'seat-window'
            }
            else if(type === 1)
            {
                seat.className = 'seat-free'
            }
            
            

            const checkbox = document.createElement('input');
            checkbox.type = 'checkbox';
            checkbox.disabled = taken;
            checkbox.id = `seat-${eID}`;
            checkbox.name = `seat-${eID}`;
            checkbox.value = `${eID}`;

            const label = document.createElement('label');
            label.textContent = `Nr ${eID}`;
            label.htmlFor = `seat-${eID}`;

            seat.appendChild(checkbox);
            seat.appendChild(label);
            seatingContainer.appendChild(seat);
            eID++;
        }
    }
}

const urlParams = new URLSearchParams(window.location.search);
const flightID = urlParams.get('ID');
displayData(flightID);
generateSeatingPlan(10, 4, flightID); 