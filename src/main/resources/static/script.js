let globalDetailCounter = 2;
let globalItemCounter = 2;
let usedDetailID, usedItemID;

async function detailOutput() {
    document.querySelectorAll('.detail-input').forEach(event => {
        event.addEventListener('input', (e) => {
            usedDetailID = e.target.id;
        });
    });

    let input = document.getElementById(usedDetailID).value;
    let detailListElement = document.getElementById('detailsList');
    detailListElement.replaceChildren();

    if (Array.from(input)[0] === ' ' || input === '') return;

    let response = await fetch('api/detail/search', {
        method: 'POST',
        body: input
    });

    let detailList = await response.json();

    detailList.forEach(detail => {
        let detailElement = document.createElement('option');
        detailElement.innerText = detail.detailName;

        detailListElement.appendChild(detailElement);
    });
}

async function itemOutput() {
    document.querySelectorAll('.item-input').forEach(event => {
        event.addEventListener('input', (e) => {
            usedItemID = e.target.id;
        });
    });

    let input = document.getElementById(usedItemID).value;
    let itemListElement = document.getElementById('itemsList');
    itemListElement.replaceChildren();

    if (Array.from(input)[0] === ' ' || input === '') return;

    let response = await fetch('api/item/search', {
        method: 'POST',
        body: input
    });

    let itemList = await response.json();

    itemList.forEach(item => {
        let itemElement = document.createElement('option');
        itemElement.innerText = item.itemName;

        itemListElement.appendChild(itemElement);
    });
}

function addDetailSearch() {
    let detailInput = document.createElement('input');

    detailInput.setAttribute('class', 'detail-input');
    detailInput.setAttribute('type', 'search');
    detailInput.setAttribute('list', 'detailsList');
    detailInput.setAttribute('id', `detail-input-id-${globalDetailCounter}`);
    detailInput.setAttribute('placeholder', 'Поиск деталей');
    detailInput.setAttribute('oninput', 'detailOutput()');

    let element = document.getElementById('detailBlock');
    element.appendChild(detailInput);

    globalDetailCounter++;
}

function removeDetailSearch() {
    let element = document.getElementById(`detail-input-id-${globalDetailCounter - 1}`);
    element.remove();
    globalDetailCounter--;
}

function addItemSearch() {
    let itemInput = document.createElement('input');

    itemInput.setAttribute('class', 'item-input');
    itemInput.setAttribute('type', 'search');
    itemInput.setAttribute('list', 'itemsList');
    itemInput.setAttribute('id', `item-input-id-${globalItemCounter}`);
    itemInput.setAttribute('placeholder', 'Поиск изделий');
    itemInput.setAttribute('oninput', 'itemOutput()');

    let element = document.getElementById('itemBlock');
    element.appendChild(itemInput);

    globalItemCounter++;
}

function removeItemSearch() {
    let element = document.getElementById(`item-input-id-${globalItemCounter - 1}`);
    element.remove();
    globalItemCounter--;
}

function createOrder() {
    let detailList = document.querySelectorAll('.detail-input');
    let detailArray = [];

    let itemList = document.querySelectorAll('.item-input');
    let itemArray = [];

    detailList.forEach(detail => detailArray.push(detail.value));
    itemList.forEach(item => itemArray.push(item.value));

    let orderDetails = document.getElementById('order-details').value;


    let orderData = {
        detailList: detailArray,
        itemList: itemArray,
        orderDetails: orderDetails
    };

    fetch('api/order/create', {
        method: 'POST',
        headers: {
            "Content-Type": "application/json; charset=utf-8"
        },
        body: JSON.stringify(orderData)
    }).then(async response => alert(await response.text()));
}