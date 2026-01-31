import './CustomerForm.css';

const CutomerForm = ({customerName, mobileNumber, setMobileNumber, setCustomerName}) => {
  return (
    <div className= " p-3">
      <div className='mb-3'>
      <div className="d-flex align-items-center gap-2">
        <label htmlFor="CustomerName" className='col-4'> Customer Name</label>
        <input type="text" id="CustomerName" className='form-control-sm' onChange={(e) => setCustomerName(e.target.value)} value={customerName} />
      </div>
      </div>

      <div className='mb-3'>
      <div className="d-flex align-items-center gap-2">
        <label htmlFor="mobileNumber" className='col-4'> Mobile number</label>
        <input type="text" id="mobileNumber" className='form-control-sm' onChange={(e) => setMobileNumber(e.target.value)} value={mobileNumber} />
      </div>
      </div>

    </div>
  )
}

export default CutomerForm;
