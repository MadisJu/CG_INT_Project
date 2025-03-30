const urlParams = new URLSearchParams(window.location.search);
const flightID = urlParams.get('ID');
const passengers = urlParams.get('passengers');

async function displayData(id) 
{
    const apiUrl = 'http://localhost:8080/api/flight?ID=' + id;
    const response = await fetch(apiUrl);
    const data = await response.json();

    document.getElementById("route-placeholder").textContent = `${data.algkoht} -> ${data.sihtkoht}`;
    document.getElementById("departure-placeholder").textContent = `Departure: ${data.lahkumisetund}:${data.lahkumiseminutid}`;
    document.getElementById("duration-placeholder").textContent = `Duration: ${data.lennuaeg} h`;
    document.getElementById("date-placeholder").textContent = `Date: ${data.kuupäev}`;
    document.getElementById("price-placeholder").textContent = `Price per economy seat: ${data.hind} €`;
}

async function generateSeatingPlan(rows, columns, id) 
{
    const apiUrl = 'http://localhost:8080/api/seats?ID=' + id;
    const response = await fetch(apiUrl);
    const data = await response.json();

    const suggestionUrl = 'http://localhost:8080/api/suggestedseats?ID=' + id + '&passengers=' + passengers;
    const suggestresponse = await fetch(suggestionUrl);
    const suggestdata = await suggestresponse.json();
    console.log(suggestdata);
    const seatingContainer = document.getElementById("seating-container");
    seatingContainer.innerHTML = "";

    let eID = 1;

    for (let row = 1; row <= rows; row++) 
    {
        for (let col = 1; col <= columns; col++) 
        {
            if(col === 4)
            {
                const gap = document.createElement('div');
                seatingContainer.appendChild(gap);
                continue
            }
            const seat = document.createElement('div');
            seat.className = 'seat'
            
            let type = data[eID-1].seatType;
            
            let taken = data[eID-1].taken;
            if(taken === true)
            {
                seat.classList.add('seat-taken');
            }
            else if(suggestdata.includes(eID))
            {
                seat.classList.add('seat-suggested');
            }
            else if(type === 3)
            {
                seat.classList.add('seat-first');
            }
            else if(type === 2)
            {
                seat.classList.add('seat-window');
            }
            else if(type === 1)
            {
                seat.classList.add('seat-free');
            }
            
            
            

            const checkbox = document.createElement('input');
            checkbox.type = 'checkbox';
            checkbox.disabled = taken;
            checkbox.id = `seat-${eID}`;
            checkbox.name = taken === false ? `seat-${eID}` : 'disabled';
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

    selectionLogic();
}

function selectionLogic()
{
    checkboxes = Array.from(document.querySelectorAll("input[type='checkbox']")).filter(checkbox => !checkbox.disabled);

    function maxSelected() 
    {
        const checkedBoxes = Array.from(checkboxes).filter(checkbox => checkbox.checked);

        checkboxes.forEach(checkbox => {
            if (!checkbox.checked) 
            {
                checkbox.parentElement.classList.remove('seat-selected')
            }
            else
            {
                checkbox.parentElement.classList.add('seat-selected')
            }
        });

        if (checkedBoxes.length >= passengers) 
        {
            checkboxes.forEach(checkbox => {
                if (!checkbox.checked) 
                {
                    checkbox.disabled = true;
                }
                else
                {
                }
            });
        } 
        else
        {
            checkboxes.forEach(checkbox => {
            if (checkbox.disabled) 
            {
                checkbox.disabled = false;
            }
            });
        }
    }

    
    checkboxes.forEach(checkbox => {
        checkbox.addEventListener('change', maxSelected);
    });

}

displayData(flightID);
generateSeatingPlan(10, 7, flightID);



