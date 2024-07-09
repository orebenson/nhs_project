window.addEventListener('DOMContentLoaded', () => {
    const collapseNav = document.querySelector('.collapse-nav');
    const menuToggle = document.querySelector('.header-toggle-nav')
    menuToggle.addEventListener('click', () => {
        if (collapseNav.hidden) {
            collapseNav.hidden = false;
            collapseNav.setAttribute('aria-hidden', 'false');
            menuToggle.classList.add('active');
        } else {
            collapseNav.hidden = true;
            collapseNav.setAttribute('aria-hidden', 'true');
            menuToggle.classList.remove('active');
        }
    })

    const homeLogo = document.querySelector('.nhs-logo')
    homeLogo.addEventListener('click', () => {
        window.location.href = '/';
    })
})