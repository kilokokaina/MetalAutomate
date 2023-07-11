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

    detailList.forEach(detail => {
        let detailElement = document.createElement('option');
        detailElement.innerText = detail.detailName;

        detailListElement.appendChild(detailElement);
    });

    console.log(detailList);

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

    itemList.forEach(item => {
        let itemElement = document.createElement('option');
        itemElement.innerText = item.itemName;

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
    // let detailList = document.querySelectorAll('.detail-input');
    // let detailArray = [];
    //
    // let itemList = document.querySelectorAll('.item-input');
    // let itemArray = [];
    //
    // detailList.forEach(detail => detailArray.push(detail.value));
    // itemList.forEach(item => itemArray.push(item.value));
    //
    // let orderDetails = document.getElementById('order-details').value;
    //
    //
    // let orderData = {
    //     detailList: detailArray,
    //     itemList: itemArray,
    //     orderDetails: orderDetails
    // };
    //
    // fetch('api/order/create', {
    //     method: 'POST',
    //     headers: {
    //         "Content-Type": "application/json; charset=utf-8"
    //     },
    //     body: JSON.stringify(orderData)
    // }).then(async response => alert(await response.text()));
    fetch('api/order/test', {
        method: 'POST',
        headers: {
            "Content-Type": "application/json; charset=utf-8"
        },
        body: JSON.stringify(detailRequestArray)
    }).then();

    console.log(itemRequestArray);
    //
    // fetch("api/order/test_item", {
    //     method: 'POST',
    //     headers: {
    //         "Content-Type": "application/json; charset=utf-8"
    //     },
    //     body: JSON.stringify(detailRequestArray)
    // }).then();
}