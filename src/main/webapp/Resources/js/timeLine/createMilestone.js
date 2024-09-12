const createMilestoneSkeleton = function (quantity) {
    const cardHtml = `
   <div class="Milestone">
                    <div class="ContinerShiftData">
                        <p name="patientFirstName"></p>
                        <p name="shiftReason"></p>
                        <p name="patientDisability"></p>
                    </div>
                    <div class="ContainerDateTime">
                        <i class="clock"></i>
                        <p name="schedulingTime"></p>
                    </div>
                </div>
                `;
    return cardHtml.repeat(quantity);
};
export { createMilestoneSkeleton };
