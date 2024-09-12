const createRowsSqueleton = function (quantityRows) {
    const htmlRowSqueleton = `
     <tr>
        <td>Sophia Rodriguez</td>
        <td>Dr. Isabella Garcia</td>
        <td>07-18-2024</td>
        <td>sophia.rodriguez@email.com</td>
        <td>85</td>
        <td>...</td>
    </tr>
    `;
    return htmlRowSqueleton.repeat(quantityRows);
};
export { createRowsSqueleton };
