function logOutPopup() {
    if(confirm("Do yaou want to logout?")) {
        window.location = "/"
    } else {
        location.reload()
    }
}

