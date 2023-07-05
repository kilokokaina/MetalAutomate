async function detailOutput() {
    let input = document.getElementById('detail-input-id').value;

    if (Array.from(input)[0] !== ' ' || input !== '') {
        let response = await fetch('api/detail/search', {
            method: 'POST',
            body: input
        });

        let detailList = response.json();
        console.log(detailList);
    }
}

function itemOutput() {
    let input = document.getElementById('item-input-id').value;

    console.clear();
    console.log(input);
}

function constOutput() {
    let input = document.getElementById('const-input-id').value;

    console.clear();
    console.log(input);
}