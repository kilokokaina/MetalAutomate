let globalDetailCounter = 2;
let globalItemCounter = 2;
let usedDetailID, usedItemID;

let detailRequestArray = [];
let itemRequestArray = [];

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

    let detailCount = new Map();
    for (let i = 0; i < detailList.length; i++) {
        let counter = 0;
        for (let j = 0; j < detailList.length; j++) {
            if (detailList[i].detailName === detailList[j].detailName) counter++;
        }

        detailCount.set(detailList[i].detailName, counter);
    }

    detailList.forEach(detail => {
        let detailElement = document.createElement('option');

        if (detailCount.get(detail.detailName) > 1) {
            detailElement.innerText = `${detail.detailName} (${detail.detailDescribe})`;
        } else detailElement.innerText = detail.detailName;

        detailListElement.appendChild(detailElement);
    });

    detailRequestArray[usedDetailID.split('_')[1] - 1] = {
        id: detailList[0].id,
        detailName: detailList[0].detailName,
        detailDescribe: detailList[0].detailDescribe
    };
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

    let itemCount = new Map();
    for (let i = 0; i < itemList.length; i++) {
        let counter = 0;
        for (let j = 0; j < itemList.length; j++) {
            if (itemList[i].itemName === itemList[j].itemName) counter++;
        }

        itemCount.set(itemList[i].itemName, counter);
    }

    itemList.forEach(item => {
        let itemElement = document.createElement('option');

        if (itemCount.get(item.itemName) > 1) {
            itemElement.innerText = `${item.itemName} (${item.itemDescribe})`;
        } else itemElement.innerText = item.itemName;

        itemListElement.appendChild(itemElement);
    });

    itemRequestArray[usedItemID.split('_')[1] - 1] = {
        id: itemList[0].id,
        itemName: itemList[0].itemName,
        itemDescribe: itemList[0].itemDescribe,
        detailList: itemList[0].detailList
    };
}

function addDetailSearch() {
    let detailInput = document.createElement('input');

    detailInput.setAttribute('class', 'detail-input');
    detailInput.setAttribute('type', 'search');
    detailInput.setAttribute('list', 'detailsList');
    detailInput.setAttribute('id', `detail-input-id_${globalDetailCounter}`);
    detailInput.setAttribute('placeholder', 'Поиск деталей');
    detailInput.setAttribute('autocomplete', 'off');
    detailInput.setAttribute('oninput', 'detailOutput()');

    let element = document.getElementById('detailBlock');
    element.appendChild(detailInput);

    globalDetailCounter++;
}

function removeDetailSearch() {
    let element = document.getElementById(`detail-input-id_${globalDetailCounter - 1}`);
    element.remove();

    detailRequestArray.pop();

    globalDetailCounter--;
}

function addItemSearch() {
    let itemInput = document.createElement('input');

    itemInput.setAttribute('class', 'item-input');
    itemInput.setAttribute('type', 'search');
    itemInput.setAttribute('list', 'itemsList');
    itemInput.setAttribute('id', `item-input-id_${globalItemCounter}`);
    itemInput.setAttribute('placeholder', 'Поиск изделий');
    itemInput.setAttribute('autocomplete', 'off');
    itemInput.setAttribute('oninput', 'itemOutput()');

    let element = document.getElementById('itemBlock');
    element.appendChild(itemInput);

    globalItemCounter++;
}

function removeItemSearch() {
    let element = document.getElementById(`item-input-id_${globalItemCounter - 1}`);
    element.remove();
    globalItemCounter--;
}

function createOrder() {
    let orderData = {
        detailList: detailRequestArray,
        itemList: itemRequestArray,
        orderDetails: document.getElementById('order-details').value
    };

    console.log(orderData);

    fetch('api/order/create', {
        method: 'POST',
        headers: {
            "Content-Type": "application/json; charset=utf-8"
        },
        body: JSON.stringify(orderData)
    }).then(async response => alert(await response.text()));
}