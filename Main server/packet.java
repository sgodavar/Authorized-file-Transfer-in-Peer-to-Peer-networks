
import java.io.*;
import java.util.Vector;

class packet implements java.io.Serializable
{
	String Duser,Cuser,Dpass,ccmech,Cpass,Dmname,Cmname,CDpeer,requser,reqkeyword,action,fname,target,key,name,user1,user2,user3,ans;
	int Dport,Cport, ccport;
	Vector msgg;
	String ddpeer,dcupeer,dhpeer,file,content,newkey,newword;
	
	
	public void setaction(String act)
	{
		this.action=act;
	}
	public String getaction()
	{
		return action;
	}
	
	
	public void setnewkey(String nk)
	{
		newkey=nk;
	}
	public String getnewkey()
	{
		return newkey;
	}
	
		
	public void setnewword(String nwk)
	{
		newword=nwk;
	}
	public String getnewword()
	{
		return newword;
	}

	
	
	
	
	
	public void setDuser(String dname)
	{
		Duser=dname;
	}
	public void setccport(int vvport)
	{
		ccport=vvport;
	}
	public int getccport()
	{
		return ccport;
	}
	
public void setcontent(String con)
	{
		content=con;
	}
	public String getcontent()
	{
		return content;
	}	
	
public void setccmech(String ccpass)
	{
		ccmech=ccpass;
	}
	public String getccmech()
	{
		return ccmech;
	}
public void setddpeer(String DDpeer)
	{
		ddpeer=DDpeer;
	}
	public String getddpeer()
	{
		return ddpeer;
	}

	public void setdcupeer(String DCpeer)
	{
		dcupeer=DCpeer;
	}
	public String getdcupeer()
	{
		return dcupeer;
	}
	
	

	public void setdhpeer(String DHpeer)
	{
		dhpeer=DHpeer;
	}
	public String getdhpeer()
	{
		return dhpeer;
	}



	public void setfile(String ff)
	{
		file=ff;
	}
	public String getfile()
	{
		return file;
	}







	
	
	
	public String getDuser()
	{
		return Duser;
	}
	public void setDpass(String dpass)
	{
		Dpass=dpass;
	}
	public String getDpass()
	{
		return Dpass;
	}
	public void setDmname(String dmach)
	{
		Dmname=dmach;
	}
	public String getDmname()
	{
		return Dmname;
	}

public void setDport(int dport)
	{
		Dport=dport;
	}
	public int getDport()
	{
		return Dport;
	}
	public void setCuser(String cname)
	{
		Cuser=cname;
	}
	public String getCuser()
	{
		return Cuser;
	}
	public void setCpass(String cpass)
	{
		Cpass=cpass;
	}
	public String getCpass()
	{
		return Cpass;
	}
	public void setCmname(String cmach)
	{
		Cmname=cmach;
	}
	public String getCmname()
	{
		return Cmname;
	}

public void setCport(int cport)
	{
		Cport=cport;
	}
	public int getCport()
	{
		return Cport;
	}
	public void setCDpeer(String peer)
	{
		CDpeer=peer;
	}
	public String getCDpeer()
	{
		return CDpeer;
	}
	
	
	public void setrequser(String ruser)
	{
	requser=ruser;	
	}
	public String getrequser()
	{
		return requser;
		
	}
	
	public void setreqkey(String rkey)
	{
		reqkeyword=rkey;
	}
	public String getreqkey()
	{
		return reqkeyword;
	}
	
	
	
	public void setKey(String Key1)
	{
		key=Key1;
	}
	
	public String getKey()
	{
		return key;
		
	}
	 public void setMsgnew(Vector s) {
                msgg =s;
        }
        public Vector getMsgnew() {
                return msgg;
        }

}

