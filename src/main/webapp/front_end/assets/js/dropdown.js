function dropdownMenu(e) {
    $('.dropdown-toggle').dropdown().dropdown('toggle');
    e.stopPropagation();
    console.log(123);
}