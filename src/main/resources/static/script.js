let globalDetailCounter = 2;
let globalItemCounter = 2;

async function detailOutput() {
    let input = document.getElementById(`detail-input-id-${globalDetailCounter - 1}`).value;
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
    let input = document.getElementById(`item-input-id-${globalItemCounter - 1}`).value;
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

    detailInput.setAttribute('type', 'search');
    detailInput.setAttribute('list', 'detailsList');
    detailInput.setAttribute('id', `detail-input-id-${globalDetailCounter}`);
    detailInput.setAttribute('placeholder', 'Поиск деталей');
    detailInput.setAttribute('oninput', 'detailOutput()');

    let element = document.getElementById('detailBlock');
    element.appendChild(detailInput);

    globalDetailCounter++;
}

function addItemSearch() {
    let itemInput = document.createElement('input');

    itemInput.setAttribute('type', 'search');
    itemInput.setAttribute('list', 'itemsList');
    itemInput.setAttribute('id', `item-input-id-${globalItemCounter}`);
    itemInput.setAttribute('placeholder', 'Поиск изделий');
    itemInput.setAttribute('oninput', 'itemOutput()');

    let element = document.getElementById('itemBlock');
    element.appendChild(itemInput);

    globalItemCounter++;
}